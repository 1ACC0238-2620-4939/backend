"""Executable Gherkin steps against an actual API; creates UUID-labelled test records.

No existing records are modified. The API has no delete contracts, so records are
retained as technical evidence. This does not represent human acceptance interviews.
"""
import argparse, json, uuid, time
from pathlib import Path
from urllib.request import Request, urlopen
from urllib.error import HTTPError

parser=argparse.ArgumentParser()
parser.add_argument('--base-url',required=True)
parser.add_argument('--output',default='tests/results/sprint1-api.json')
args=parser.parse_args()
state={}; requests=[]
def api(method,path,data=None):
    body=None if data is None else json.dumps(data).encode()
    req=Request(args.base_url.rstrip('/')+path,data=body,method=method,headers={'Content-Type':'application/json'})
    try:
        with urlopen(req,timeout=40) as res: status=res.status; raw=res.read().decode()
    except HTTPError as res: status=res.code;raw=res.read().decode()
    try:value=json.loads(raw) if raw else None
    except json.JSONDecodeError:value=raw
    requests.append({'method':method,'path':path,'request':data,'status':status,'response':value})
    return status,value
def success(method,path,data=None):
    status,result=api(method,path,data); assert 200<=status<300,(method,path,status,result);return result
def profile():
    return success('POST','/api/v1/profiles',{'userReferenceId':str(uuid.uuid4()),'givenNames':'TB1 Technical Test','paternalSurname':'Evidence','maternalSurname':'Only'})
def given(step):
    if step=='a new test vehicle':
        import random,string
        state['plate']=''.join(random.choices(string.ascii_uppercase,k=3))+'-'+str(random.randrange(100,1000)); state['vehicle']=success('POST','/api/v1/vehicles',{'plateNumber':state['plate'],'capacity':1500})
    elif step=='a new test profile':state['profile']=profile()
    elif step=='a new test driver':
        state['profile']=profile();state['driver']=success('POST','/api/v1/drivers',{'profileId':state['profile']['profileId'],'licenseNumber':'A'+str(int(uuid.uuid4().hex[:8],16)%90000000+10000000)})
    elif step=='a trip identifier that does not exist':state['missing']=str(uuid.uuid4())
    elif step=='a new scheduled test trip':
        given('a new test vehicle');given('a new test driver')
        state['trip']=success('POST','/api/v1/trips',{'driverId':state['driver']['driverId'],'vehicleId':state['vehicle']['vehicleId'],'originAddress':'TB1 test origin','originLatitude':-12.04,'originLongitude':-77.03,'destinationAddress':'TB1 test destination','destinationLatitude':-12.12,'destinationLongitude':-77.02,'scheduledAt':'2026-10-06T12:00:00Z','distanceKm':15,'durationMinutes':45,'routeReference':'tb1-test-'+uuid.uuid4().hex,'calculatedAt':'2026-10-05T12:00:00Z'})
    else:raise ValueError('Unimplemented Given: '+step)
def when(step):
    if step=='its capacity is changed':success('PATCH','/api/v1/vehicles/'+state['vehicle']['vehicleId']+'/capacity',{'capacity':2500})
    elif step=='the trip starts and completes':
        path='/api/v1/trips/'+state['trip']['tripId'];success('PATCH',path+'/start',{'startedAt':'2026-10-06T12:00:00Z'});success('PATCH',path+'/complete',{'completedAt':'2026-10-06T13:00:00Z'})
    elif step=='its details are requested':state['missing_response']=api('GET','/api/v1/trips/'+state['missing'])
    elif step=='its full name is changed':success('PATCH','/api/v1/profiles/'+state['profile']['profileId']+'/full-name',{'givenNames':'Updated TB1 Test','paternalSurname':'Evidence','maternalSurname':'Only'})
    elif step=='the driver is deactivated and activated':
        path='/api/v1/drivers/'+state['driver']['driverId'];success('PATCH',path+'/deactivate');assert success('GET',path)['status']=='INACTIVE';success('PATCH',path+'/activate')
    else:raise ValueError('Unimplemented When: '+step)
def then(step):
    if step=='its identifier and capacity are persisted':
        v=success('GET','/api/v1/vehicles/'+state['vehicle']['vehicleId']);assert v['plateNumber']==state['plate'].replace('-','');assert v['capacity']==2500
    elif step=='the completed trip rejects cancellation':
        path='/api/v1/trips/'+state['trip']['tripId'];assert success('GET',path)['status']=='COMPLETED';status,_=api('PATCH',path+'/cancel',{'cancelledAt':'2026-10-06T14:00:00Z'});assert status in (400,409,422),status
    elif step=='the response is not found':assert state['missing_response'][0]==404,state['missing_response']
    elif step=='the updated full name can be read':
        p=success('GET','/api/v1/profiles/'+state['profile']['profileId']);assert 'Updated TB1 Test' in json.dumps(p),p
    elif step=='the driver is active with its profile reference':
        d=success('GET','/api/v1/drivers/'+state['driver']['driverId']);assert d['status']=='ACTIVE';assert d['profileId']==state['profile']['profileId']
    else:raise ValueError('Unimplemented Then: '+step)
scenarios=[];current=None
for line in (Path(__file__).parents[1]/'features'/'sprint1.feature').read_text().splitlines():
    line=line.strip()
    if line.startswith('Scenario:'):
        current={'name':line.split(':',1)[1].strip(),'steps':[]};scenarios.append(current)
    elif line.startswith(('Given ','When ','Then ')):
        keyword,step=line.split(' ',1);current['steps'].append((keyword,step))
results=[]
for scenario in scenarios:
    state.clear();start=len(requests)
    try:
        for keyword,step in scenario['steps']:{'Given':given,'When':when,'Then':then}[keyword](step)
        result={'scenario':scenario['name'],'status':'PASS'}
    except Exception as error:result={'scenario':scenario['name'],'status':'FAIL','error':str(error)}
    result['requests']=requests[start:];results.append(result);print(result['status'],result['scenario'],result.get('error',''))
out=Path(args.output);out.parent.mkdir(parents=True,exist_ok=True)
out.write_text(json.dumps({'base_url':args.base_url,'utc_epoch':time.time(),'results':results},indent=2),encoding='utf-8')
raise SystemExit(0 if all(r['status']=='PASS' for r in results) else 1)
