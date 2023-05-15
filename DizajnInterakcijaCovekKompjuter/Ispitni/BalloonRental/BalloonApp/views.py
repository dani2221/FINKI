from django.shortcuts import render, redirect
from .models import Flight
from .forms import FlightForm
# Create your views here.
def index(request):
    return render(request, "index.html")

def flights(request):
    if request.method == "POST":
        form_data = FlightForm(data=request.POST, files=request.FILES)
        if form_data.is_valid():
            book = form_data.save(commit=False)
            book.user_created = request.user
            book.photo = form_data.cleaned_data['photo']
            book.save()
            return redirect("flights")

    data = Flight.objects.filter(user_created=request.user).all()
    context = {"flights": data, "form": FlightForm}
    return render(request, "flights.html", context=context)

