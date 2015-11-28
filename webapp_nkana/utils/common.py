from pymongo import MongoClient
from django.conf import settings


def mongo_instance():
    # TBD need to move configuration to global settings
    client = MongoClient('localhost', 27017)
    db = getattr(client, 'paypal')
    db.authenticate('appuser', 'apppassword')
    return db
