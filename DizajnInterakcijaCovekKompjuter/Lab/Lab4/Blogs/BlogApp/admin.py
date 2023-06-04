from django.contrib import admin

from BlogApp.models import BUser, Comment, BlogPost


class BUserAdmin(admin.ModelAdmin):
    def has_change_permission(self, request, obj=None):
        if obj is None:
            return True
        return request.user == obj.user


class BlogPostAdmin(admin.ModelAdmin):
    search_fields = ("title", "content")
    list_filter = ("date_created",)
    list_display = ("title", "user")

    def has_change_permission(self, request, obj=None):
        if obj is None:
            return True
        return request.user == obj.user.user

    def has_view_permission(self, request, obj=None):
        if obj is None:
            return True
        return request.user not in list([el.user for el in obj.blocked_users])


class CommentAdmin(admin.ModelAdmin):

    list_display = ("content", "date")

    def has_delete_permission(self, request, obj=None):
        if obj is None:
            return True
        return request.user == obj.blog_post.user.user or request.user == obj.user.user


admin.site.register(BUser, BUserAdmin)
admin.site.register(Comment, CommentAdmin)
admin.site.register(BlogPost, BlogPostAdmin)