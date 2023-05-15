from django import forms
from .models import Flight

class FlightForm(forms.ModelForm):
    
    def __init__(self, *args, **kwargs):
        super(FlightForm, self).__init__(*args, **kwargs)
        for field in self.visible_fields():
            print(field)
            field.field.widget.attrs["class"] = "form-control"

    class Meta:
        model = Flight
        exclude = ('user_created',)