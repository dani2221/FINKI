from django.shortcuts import render
from .models import BlogPost


# Create your views here.
def posts(request):
    qs = BlogPost.objects.filter().all()
    print(qs)
    context = {"posts": qs}
    return render(request, "posts.html", context=context)

