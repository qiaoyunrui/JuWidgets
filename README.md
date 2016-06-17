# JuWidgets

[![](https://jitpack.io/v/qiaoyunrui/JuWidgets.svg)](https://jitpack.io/#qiaoyunrui/JuWidgets)

The wharehouse of weights.

## 1. JuProgressbar
  
  a little good progressbar like this:
  
  ![pic1](http://ww1.sinaimg.cn/mw690/005TG3l2jw1f4xyf7q84cj30qo1be75t.jpg)
  
  ![pic2](http://ww1.sinaimg.cn/mw690/005TG3l2jw1f4xyf82aglj30qo1bejt5.jpg)
  
  ![pic3](http://ww2.sinaimg.cn/mw690/005TG3l2jw1f4xyf8jagmj30qo1beabj.jpg)
  
  This progressbar provide some new properties:
  
    * loadingText：the text of the loadingTextView
    * colorNum：the amount of colors
    * color1:first color
    * color2:second color
    * color3:third color
    * color4:forth color
  
  You can use this progressbar like this:

  Step 1. Add the JitPack repository to your build file
    
    Add it in your root build.gradle at the end of repositories:
    ```java
    allprojects {
		  repositories {
			...
			maven { url "https://jitpack.io" }
		  }
	  }
    ```
    
  Step 2. Add the dependency
    
    ```java
    dependencies {
	        compile 'com.github.qiaoyunrui:JuWidgets:1.1'
	  }
    ```
  
  Step 3. Add this view to your XML
 	
    ```html
    
      <com.juhezi.juprogressbar.View.JuProgressbar
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerInParent="true"
        android:visibility="visible"
        juhezi:color1="@color/blue"
        juhezi:color2="@color/red"
        juhezi:color3="@color/yellow"
        juhezi:color4="@color/green"
        juhezi:loadingText=""
        juhezi:colorNum="4">
     </com.juhezi.juprogressbar.View.JuProgressbar>
     
    ```
  
## 2 .loading....
  
