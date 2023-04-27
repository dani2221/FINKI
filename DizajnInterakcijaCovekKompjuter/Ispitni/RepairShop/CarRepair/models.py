from django.db import models
from django.contrib.auth.models import User

class Manufacturer(models.Model):
    name = models.CharField(max_length=50)
    owner = models.CharField(max_length=50)
    origin = models.CharField(max_length=50)
    link = models.URLField()

    def __str__(self):
	    return self.name



class Car(models.Model):
    type = models.CharField(max_length=50)
    manufacturer = models.ForeignKey(Manufacturer, on_delete=models.CASCADE, null=True, blank=True)
    color = models.CharField(max_length=50)
    speed = models.IntegerField()
    def __str__(self):
	    return self.type + " " + self.manufacturer.name

class Workshop(models.Model):
    name = models.CharField(max_length=50)
    year = models.CharField(max_length=4)
    oldTimerRepair = models.BooleanField()
    manufacturers = models.ManyToManyField(Manufacturer)

    def __str__(self):
	    return self.name



class Repair(models.Model):
    code = models.CharField(max_length=50)
    date = models.DateField()
    description = models.TextField()
    user = models.ForeignKey(User, on_delete=models.CASCADE, null=True, blank=True)
    image = models.ImageField()
    car = models.ForeignKey(Car, on_delete=models.CASCADE, null=True, blank=True)
    workshop = models.ForeignKey(Workshop, on_delete=models.CASCADE, null=True, blank=True)

    def __str__(self):
	    return self.code + " " + self.code

# class WorkshopManufacturer(models.Model):
#     workshop = models.ForeignKey(Workshop, on_delete=models.CASCADE, null=True, blank=True)
#     manufacturer = models.ForeignKey(Manufacturer, on_delete=models.CASCADE, null=True, blank=True)

