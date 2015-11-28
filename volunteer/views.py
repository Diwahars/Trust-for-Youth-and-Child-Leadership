from django.shortcuts import render
from webapp_nkana.utils.common import mongo_instance
from datetime import datetime

from rest_framework.views import APIView
from rest_framework.response import Response
from django.shortcuts import render
from django.http import HttpResponseRedirect, HttpResponse
from rest_framework.authentication import SessionAuthentication, BasicAuthentication
from rest_framework.permissions import IsAuthenticated


def register_profile(request):

    if request.method == 'POST':

        db = mongo_instance()
        keys = db.tycl.find_one({'id': 'volunteer_profile_reference'})['val']
        for k in keys:
            if k in ('username', ):
                continue
            keys[k] = request.POST[k.replace(' ', '_')]
        keys['username'] = request.user.username
        db.tycl_vol_pfle.insert(keys)
        return HttpResponseRedirect('/')

    return render(request, 'profile.html')


class Volunteer(APIView):

    def get(self, request):
        if 'is_profile_set' in request.GET:
            return Response({"status": False})

        if 'retrive_profile' in request.GET:
            db = mongo_instance()
            res = db.tycl_vol_pfle.find_one({'username': request.GET['retrive_profile']}) or {}
            if '_id' in res:
                del res['_id']
            return Response(res)
