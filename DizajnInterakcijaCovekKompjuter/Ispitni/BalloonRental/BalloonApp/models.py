from django.db import models
from django.contrib.auth.models import User


class Pilot(models.Model):
    name = models.TextField()
    surname = models.TextField()
    total_hours = models.IntegerField()
    rank = models.TextField()

class Baloon(models.Model):
    type = models.TextField()
    manufacturer = models.TextField()
    capacity = models.IntegerField()

class Airline(models.Model):
    name = models.TextField()
    year = models.IntegerField()
    flies_europe = models.BooleanField()
    cooperation_pilots = models.ManyToManyField(Pilot)

class Flight(models.Model):
    code = models.CharField(max_length=10)
    name_airport_departure = models.TextField()
    name_airport_arrival = models.TextField()
    user_created = models.ForeignKey(User, on_delete=models.CASCADE, null=True, blank=True)
    photo = models.ImageField()
    pilot = models.ForeignKey(Pilot, on_delete=models.CASCADE)
    airline = models.ForeignKey(Airline, on_delete=models.CASCADE)
