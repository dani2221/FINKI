from django.shortcuts import render, redirect
from .models import Product
from .forms import ProductForm


def index(request):
    return render(request, "index.html")


def out_of_stock(request):
    if request.method == "POST":
        form = ProductForm(request.POST, files=request.FILES)
        if form.is_valid():
            product = form.save(commit=False)
            product.user = request.user
            product.photo = form.cleaned_data['photo']
            product.save()
            return redirect("/outofstock")

    qs = Product.objects.filter(stock=0, category__active=True).all()
    return render(request, "outofstock.html", {"products": qs, "form": ProductForm})
