from django.conf.urls import patterns, url
from rest_framework.urlpatterns import format_suffix_patterns

from mentor import views

urlpatterns = patterns('',
                       url(r'^profile/', views.register_mentor_profile, name='mentor_profile'),
                       url(r'^landing/', views.mentor_landing, name='mentor_landing'),
                       url(r'^questionnare/', views.mentor_questionnare, name='questionnare'),
                       )

# urlpatterns = format_suffix_patterns(urlpatterns)
