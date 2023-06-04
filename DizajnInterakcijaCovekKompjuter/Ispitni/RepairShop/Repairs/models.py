from django.contrib.auth.models import User
from django.db import models


# Create your models here.
class Manufacturer(models.Model):
    name = models.CharField(max_length=255)
    url = models.URLField()
    country = models.CharField(max_length=255)
    owner_name = models.CharField(max_length=255)

    def __str__(self):
        return self.name


class Workshop(models.Model):
    name = models.CharField(max_length=255)
    year = models.IntegerField()
    oldtimer_repair = models.BooleanField()

    def __str__(self):
        return self.name


class Car(models.Model):
    type = models.CharField(max_length=255)
    manufacturer = models.ForeignKey(Manufacturer, on_delete=models.CASCADE)
    speed = models.IntegerField()
    color = models.CharField(max_length=255)

    def __str__(self):
        return f'{self.type} {self.manufacturer}'


class Repair(models.Model):
    code = models.CharField(max_length=255)
    date = models.DateField()
    description = models.TextField()
    user = models.ForeignKey(User, on_delete=models.CASCADE)
    image = models.ImageField(upload_to='repairs/')
    car = models.ForeignKey(Car, on_delete=models.CASCADE)
    workshop = models.ForeignKey(Workshop, on_delete=models.CASCADE)

    def __str__(self):
        return self.code


class ManufacturerWorkshop(models.Model):
    workshop = models.ForeignKey(Workshop, on_delete=models.CASCADE)
    manufacturer = models.ForeignKey(Manufacturer, on_delete=models.CASCADE)



