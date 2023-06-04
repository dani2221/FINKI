from django.contrib import admin
from .models import ManufacturerWorkshop, Manufacturer, Car, Workshop, Repair


# Register your models here
class ManufacturerWorkshopAdmin(admin.TabularInline):
    model = ManufacturerWorkshop
    extra = 0


class WorkshopAdmin(admin.ModelAdmin):
    inlines = (ManufacturerWorkshopAdmin,)

    def has_delete_permission(self, request, obj=None):
        return False

    def has_change_permission(self, request, obj=None):
        return False


class RepairAdmin(admin.ModelAdmin):
    exclude = ('user', )

    def save_model(self, request, obj, form, change):
        obj.user=request.user
        super().save_model(request, obj, form, change)


class ManufacturerAdmin(admin.ModelAdmin):
    list_display = ('name',)

    def has_add_permission(self, request):
        return request.user.is_superuser


class CarAdmin(admin.ModelAdmin):
    list_display = ('type', 'speed')


admin.site.register(Car, CarAdmin)
admin.site.register(Manufacturer, ManufacturerAdmin)
admin.site.register(Workshop, WorkshopAdmin)
admin.site.register(Repair, RepairAdmin)

