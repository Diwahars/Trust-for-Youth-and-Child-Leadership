from django.shortcuts import render
from django.http import HttpResponseRedirect
from webapp_nkana.utils.common import mongo_instance


def register_mentor_profile(request):
    if request.method == 'POST':
        db = mongo_instance()
        keys = db.tycl.find_one({'id': 'mentor_profile_reference'})['val']
        for k in keys:
            if k in ('username', ):
                continue
            elif k == "Area_of_Expertise":
                keys[k] = request.POST[k.replace(' ', '_')].lower().split(',')
            keys[k] = request.POST[k.replace(' ', '_')]
        keys['username'] = request.user.username
        db.tycl_mentor_profile.insert(keys)
        return HttpResponseRedirect('/')

    return render(request, 'profile.html')
