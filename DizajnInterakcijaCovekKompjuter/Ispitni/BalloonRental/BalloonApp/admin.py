from django.contrib import admin
from .models import Flight, Baloon, Airline, Pilot

class FlightAdmin(admin.ModelAdmin):

    fields = ('code', 'name_airport_departure', 'name_airport_arrival', 'photo', 'pilot', 'airline')

    list_display = ('code', 'user_created')

    def save_model(self, request, obj, form, change):
        obj.user_created = request.user
        super().save_model(request, obj, form, change)

    def has_change_permission(self, request, obj=None):
        if obj == None:
            return True
        return request.user == obj.user_created
    
    def has_delete_permission(self, request, obj=None) -> bool:
        return False

class PilotAdmin(admin.ModelAdmin):
    list_display = ('name', 'surname')

class AirlineAdmin(admin.ModelAdmin):
    list_display = ('name', )


# Register your models here.
admin.site.register(Flight, FlightAdmin)
admin.site.register(Baloon)
admin.site.register(Airline, AirlineAdmin)
admin.site.register(Pilot, PilotAdmin)
