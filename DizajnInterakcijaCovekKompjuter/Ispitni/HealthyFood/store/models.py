from django.contrib.auth.models import User
from django.db import models


# Create your models here.
class Client(models.Model):
    name = models.CharField(max_length=255)
    surname = models.CharField(max_length=255)
    address = models.CharField(max_length=255)
    email = models.EmailField()

    def __str__(self):
        return f'{self.name}'


class Category(models.Model):
    name = models.CharField(max_length=255)
    description = models.TextField()
    active = models.BooleanField()

    def __str__(self):
        return f'{self.name}'


class Product(models.Model):
    code = models.AutoField(primary_key=True)
    name = models.CharField(max_length=255)
    description = models.TextField()
    category = models.ForeignKey(Category, on_delete=models.CASCADE)
    user = models.ForeignKey(User, on_delete=models.CASCADE)
    photo = models.ImageField(upload_to='products/')
    price = models.FloatField()
    stock = models.IntegerField()

    def __str__(self):
        return f'{self.code} {self.name}'


class Sale(models.Model):
    date = models.DateField()
    client = models.ForeignKey(Client, on_delete=models.CASCADE)

    def __str__(self):
        return f'{self.date} - {self.client}'


class SaleItem(models.Model):
    product = models.ForeignKey(Product, on_delete=models.CASCADE)
    amount = models.IntegerField()
    sale = models.ForeignKey(Sale, on_delete=models.CASCADE)

    def __str__(self):
        return f'{self.sale} - {self.product}'



