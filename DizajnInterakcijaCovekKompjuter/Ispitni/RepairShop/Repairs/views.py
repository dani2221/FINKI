from django.shortcuts import render, redirect

from Repairs.models import Repair
from .forms import RepairForm


# Create your views here.
def repairs(request):
    if request.method == "POST":
        form_data = RepairForm(data=request.POST, files=request.FILES)
        if form_data.is_valid():
            repair = form_data.save(commit=False)
            repair.user = request.user
            repair.image = form_data.cleaned_data['image']
            repair.save()
            return redirect("/repairs")
    qs = Repair.objects.filter(user=request.user, car__type='sedan')
    return render(request, "repairs.html", context={'repairs': qs, 'form': RepairForm})
