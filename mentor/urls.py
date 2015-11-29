from django.conf.urls import patterns, url
from rest_framework.urlpatterns import format_suffix_patterns

from mentor import views

urlpatterns = patterns('',
                       url(r'^profile', views.register_mentor_profile),
                       )

urlpatterns = format_suffix_patterns(urlpatterns)
