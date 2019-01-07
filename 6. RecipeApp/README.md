# Recipe App (Portfolio)
### Requirements

We need to create an application where you can swipe between different recipes. The recipes are taken from the **Food2Fork API**, here an API key is needed. Only the top rated three recipes need to be shown.

The recipes are shown using a placeholder view for a fragment. The fragments are handled in a viewpager, this viewpager sets the items. To do this, it needs to know the count of the recipes in a list. Therefor we override getCount and getItem, in getItem a new fragment is made. Because of the viewpager we can swipe between the different recipes.

TIP: For loading the images Glide could be used.

The app should look something like this (the users swipes through the recipes):

**The ingredients are optional!**

![recipe app 1](https://github.com/MarjoleinAardewijn/MobileApplicationDevelopment/blob/master/6.%20RecipeApp/recipe-app-1.png)
![recipe app 2](https://github.com/MarjoleinAardewijn/MobileApplicationDevelopment/blob/master/6.%20RecipeApp/recipe-app-2.png)
![recipe app 3](https://github.com/MarjoleinAardewijn/MobileApplicationDevelopment/blob/master/6.%20RecipeApp/recipe-app-3.png)

### Links:

Food2Fork: https://www.food2fork.com/about/api

Glide: https://github.com/bumptech/glide

Example viewpager: https://developer.android.com/training/animation/screen-slide#java
