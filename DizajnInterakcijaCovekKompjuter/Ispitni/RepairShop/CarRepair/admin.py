from django.contrib import admin

from .models import Repair, Manufacturer, Workshop, Car


class RepairAdmin(admin.ModelAdmin):
    fields = ('code', 'date', 'description', 'image', 'car', 'workshop')
    list_display = ('code', 'date', 'user')
    
    def save_model(self, request, obj, form, change):
        obj.user = request.user
        super().save_model(request, obj, form, change)
    
class WorkshopAdmin(admin.ModelAdmin):
    
    def has_change_permission(self, request, obj=None):
        return False
    
    def has_delete_permission(self, request, obj=None):
        return False

class ManufacturerAdmin(admin.ModelAdmin):
    list_display = ('name', )
    def has_add_permission(self, request):
        return request.user.is_superuser

class CarAdmin(admin.ModelAdmin):
    list_display = ('type', 'speed')

admin.site.register(Workshop, WorkshopAdmin)
admin.site.register(Repair, RepairAdmin)
admin.site.register(Manufacturer, ManufacturerAdmin)
admin.site.register(Car, CarAdmin)

# Register your models here.
