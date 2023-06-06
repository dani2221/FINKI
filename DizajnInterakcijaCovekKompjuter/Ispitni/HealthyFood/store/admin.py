from django.contrib import admin
from .models import Category, Product, SaleItem, Sale, Client


# Register your models here.
class ProductInline(admin.StackedInline):
    model = Product
    extra = 0


class CategoryAdmin(admin.ModelAdmin):
    inlines = (ProductInline,)
    list_display = ('name',)

    def has_delete_permission(self, request, obj=None):
        if obj is None:
            return True
        return request.user.is_superuser


class ProductAdmin(admin.ModelAdmin):
    exclude = ('user',)

    def has_change_permission(self, request, obj=None):
        if obj is None:
            return True
        return obj.user == request.user

    def save_model(self, request, obj, form, change):
        obj.user = request.user
        return super().save_model(request, obj, form, change)


class ClientAdmin(admin.ModelAdmin):
    list_display = ("name", "surname", )


class SaleItemInline(admin.StackedInline):
    model = SaleItem
    extra = 1


class SaleAdmin(admin.ModelAdmin):
    inlines = (SaleItemInline,)


admin.site.register(Sale, SaleAdmin)
admin.site.register(Client, ClientAdmin)
admin.site.register(Product, ProductAdmin)
admin.site.register(Category, CategoryAdmin)
