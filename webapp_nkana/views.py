from django.shortcuts import render
from django.contrib.auth import authenticate, login
from django.http import HttpResponseRedirect, HttpResponse
from django.views.decorators.csrf import csrf_exempt

from webapp_nkana.utils.common import mongo_instance
from rest_framework.views import APIView
from rest_framework.response import Response
import datetime


def index(request):
    return render(request, 'index.html')


@csrf_exempt
def app_login(request):

    if request.method == 'POST':
        username = request.POST['username']
        password = request.POST['password']
        user = authenticate(username=username, password=password)

        if 'device' in request.POST and user is not None:
            return HttpResponse('{"status": 200}')
        elif 'device' in request.POST:
            return HttpResponse('{"status": 401}')

        if user is not None:
            login(request, user)
            if user.is_active:
                return HttpResponseRedirect("/")
            else:
                return HttpResponseRedirect("/")
    else:
        return render(request, 'login.html')


def insights(request):
    day_ref = {0: 'Monday', 1: 'Tuesday', 2: 'Wednesday', 3: 'Thursday', 4: 'Friday', 5: 'Saturday', 6: 'Sunday'}
    db = mongo_instance()
    lc = db.tycl_hist_data.aggregate([{'$unwind': "$meetings"},
                                      {"$group": {"_id": "$meetings.placeOfMeeting", "count": {"$sum": 1}}}])
    location_stats = [x for x in lc]
    mentor_counts = [x for x in db.tycl_hist_data.find({}, {'mentor': 1, '_id': 0})]
    mcw = len([x for x in mentor_counts if x['mentor']])

    mc = [x for x in db.tycl_hist_data.aggregate([{'$project': {'_id': 0, 'volunteer': 1,
                                                                'meetings': {'$size': "$meetings"}}}])]

    cdays = db.tycl_hist_data.aggregate([{"$unwind":"$meetings"}, {"$project": {"_id": "$meetings.dateOfMeeting"}}])
    _days = sorted([day_ref[datetime.datetime.strptime(x['_id'].split('T')[0], '%Y-%m-%d').weekday()] for x in cdays])
    d = {x: _days.count(x) for x in _days}

    return render(request, 'insights.html', {'utilization_keys': [str(x['_id']) for x in location_stats],
                                             'utilization_values': [x['count'] for x in location_stats],
                                             'mentor': (mcw, len(mentor_counts) - mcw),
                                             'mc_m': [str(x['volunteer']) for x in mc],
                                             'mc_c': [x['meetings'] for x in mc],
                                             'df_k': str(d.keys()), 'df_v': d.values()})


class RestInsights(APIView):

    def get(self, request):
        db = mongo_instance()
        lc = db.tycl_hist_data.aggregate([{'$unwind': "$meetings"},
                                          {"$group": {"_id": "$meetings.placeOfMeeting", "count": {"$sum": 1}}}])
        location_stats = [x for x in lc]
        if 'utilization' in request.GET:
            return Response({'utilization_keys': [str(x['_id']) for x in location_stats],
                             'utilization_values': [x['count'] for x in location_stats]})
        else:
            return Response({"status": False})
