from django.conf.urls import patterns, url
from rest_framework.urlpatterns import format_suffix_patterns

from volunteer import views

urlpatterns = patterns('',
                       url(r'^rest/profile', views.Volunteer.as_view()),
                       url(r'^profile', views.register_profile),
                       )

urlpatterns = format_suffix_patterns(urlpatterns)
