from django.shortcuts import render
from django.contrib.auth import authenticate, login
from django.http import HttpResponseRedirect, HttpResponse
from django.views.decorators.csrf import csrf_exempt


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


