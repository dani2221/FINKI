from django.db import models
from django.contrib.auth.models import User


class BUser(models.Model):
    user = models.ForeignKey(User, on_delete=models.CASCADE, unique=True)
    blocked_users = models.ManyToManyField('BUser', blank=True)


class BlogPost(models.Model):
    title = models.CharField(max_length=50)
    user = models.ForeignKey(BUser, on_delete=models.CASCADE, null=True, blank=True)
    content = models.TextField()
    file = models.FileField()
    date_created = models.DateTimeField()
    date_modified = models.DateTimeField()


class Comment(models.Model):
    content = models.TextField()
    user = models.ForeignKey(BUser, on_delete=models.CASCADE, null=True, blank=True, related_name='userr')
    blog_post = models.ForeignKey(BUser, on_delete=models.CASCADE, null=True, blank=True, related_name='blogg')
    date = models.DateTimeField()

