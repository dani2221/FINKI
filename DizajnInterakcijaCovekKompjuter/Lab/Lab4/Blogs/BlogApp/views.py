from django.shortcuts import render, redirect
from .models import BlogPost, BUser
from .forms import PostForm


# Create your views here.
def posts(request):
    qs = BlogPost.objects.filter().all()
    print(qs)
    context = {"posts": qs}
    return render(request, "posts.html", context=context)


def addnew(request):
    if request.method == "POST":
        form = PostForm(request.POST, files=request.FILES)
        if form.is_valid():
            post = form.save(commit=False)
            usr = BUser.objects.filter(user=request.user).get()
            post.user = usr
            post.cover_image = form.cleaned_data['file']
            post.save()
            return redirect("/posts")
    return render(request, "addnewpost.html", context={"form": PostForm })


def profile(request):
    user = BUser.objects.get(user=request.user)
    posts = BlogPost.objects.filter(user=user)

    return render(request, "profile.html", {"user": user, "posts": posts})

