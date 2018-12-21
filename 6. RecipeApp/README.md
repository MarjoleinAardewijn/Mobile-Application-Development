# Recipe App (Portfolio)
### Requirements

We need to create an application where you can swipe between different recipes. The recipes are taken from the **Food2Fork API**, here an API key is needed. Only the top rated three recipes need to be shown.

The recipes are shown using a placeholder view for a fragment. The fragments are handled in a viewpager, this viewpager sets the items. To do this, it needs to know the count of the recipes in a list. Therefor we override getCount and getItem, in getItem a new fragment is made. Because of the viewpager we can swipe between the different recipes.

TIP: For loading the images Glide could be used.

The app should look something like this (the users swipes through the recipes):

**The ingredients are optional!**

![alt text](https://lh3.googleusercontent.com/E6fAPT-Qj34YCZrFb57AN48ouScPgVrg-xdb_ZpDQg0NhpQSnXSw5rNoMyJHdyENmpS1Vc_IWbbs_xbN5oBUz9kJAQFmIFHfnFQwVAHhfoQCT3hrcEvsQMAMdXYy9DMgs6OcoxQ4)
![alt text](https://lh6.googleusercontent.com/o8Q301cZHfR4czXNtxrOR-UpqB_PBmheCcMtBeXorq0X8zZ1g2N7BW5JPY2BKYbSN99YfgMzMA3bYOADoLwbRuRIXLVfHEJB67kt_XE-te1smHejsRgNsrz4PTE6sFHAHg0JAJZI)
![alt text](https://lh5.googleusercontent.com/uP8LcZ0IcIrEjeFauneH7Rjy1QBWE_4cNAJhqhaLcyLk18WCyfwyl8aa41Heqix5GpRdgdkMFp8qWKLb8WYq87O5q00x5JLPuWJznfZ8eH-OqzpnRNXNtKTljPqQeFk7_o8f13q8)

### Links:

Food2Fork: https://www.food2fork.com/about/api

Glide: https://github.com/bumptech/glide

Example viewpager: https://developer.android.com/training/animation/screen-slide#java
