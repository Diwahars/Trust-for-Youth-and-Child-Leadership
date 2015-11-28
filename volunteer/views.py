from django.shortcuts import render
from webapp_nkana.utils.common import mongo_instance
from datetime import datetime

from rest_framework.views import APIView
from rest_framework.response import Response
from django.shortcuts import render
from rest_framework.authentication import SessionAuthentication, BasicAuthentication
from rest_framework.permissions import IsAuthenticated


def register_profile(request):
    return render(request, 'profile.html')


class Volunteer(APIView):

    def get(self, request):
        current_user = request.user.username.upper()

        if 'is_profile_set' in request.GET:
            return Response({"status": False})
