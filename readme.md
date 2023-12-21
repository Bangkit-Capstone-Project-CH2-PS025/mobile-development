# Mobile Development Path
This is the repository CH2-PS025 Bangkit 2023 Batch 2 Product-based Capstone by Mobile Development Path

##  Members
| Bangkit ID | Name | University |
|-----|-------|------|
| A182BSY2333   | Zain Al Bani Budi Prabowo	 |  Universitas Amikom Purwokerto	 |
| A002BSY1940	   |  Samuel Christopher Swandi  | Institut Teknologi Bandung | 

# App Description
An Android application that enables users to personalize travel itineraries based on budget and activity preferences. This application build with Model–view–viewmodel (MVVM) architectural pattern.
- The model represents the app’s domain model, which can include a data model as well as business and validation logic. It communicates with the ViewModel, and lacks awareness of the View.
- The View represents the user interface of the application and holds limited, purely presentational logic that implements visual behavior. The View is completely agnostic to the business logic. In other words, the View is a “dumb” class that never contains data, nor manipulates it directly. It communicates with the ViewModel through data binding and is unaware of the Model.
- The ViewModel is the link between the View and the Model. It implements and exposes public properties and commands that the View uses by way of data binding. If any state changes occur, the ViewModel notifies the View through notification events. 

# Features
#### 1. Itinerary Planning (**Main Feature**)
Itinerary Planning is a feature that allows users to personalize their travel based on city selection, budget constraints, duration of the trip, and preferences. This functionality empowers users to intricately plan each aspect of their journey according to their desires and specific needs. The result based on input is itinerary, carbon footprint, and available transportations. 

#### 2. Find Trip
Find Trip is a feature for users to see the available trip to join, or the users can uploaded trip and encourage another users to join the trip. User can add trip to search friends and user will get the contact information of the trip owner if they join the trip.

#### 3. Budgeting
Budgeting is a feature for users to track their savings relative to their target for a trip, and user can edit or add the budget details based on several categories.

#### 4. Travel Tips
Travel tips is a feature for users to see guidance on traveling to a destination. Example: when a user go to Bali, they can see the tips and trick in Bali during vacation.

#### 5. Places
Places is a feature for users to see the list of visited places including the photo, and can add list of new place include the photos. After users adding new places, users will get the XP.

#### 6. Saved Places
Saved Places is a feature for users to see the list of saved and history itinerary, and users can see the details of ongoing itinerary. When users marked the trip as finished, users will get XP.

#### 7. Account
Account is a feature to get details of user information, dashboard of visited places, and XP with gamification level.

# Prototype
The Mobile Development team create prototype in Figma with 3 different prototypes.

[Link of Figma](https://www.figma.com/file/RYR42lEEKWaN2UAzXJPsmw/User-Interface---Bangkit-Capstone-Project?type=design&node-id=0%3A1&mode=design&t=tSsswh441EavG5gK-1)

# Architecture
https://github.com/Bangkit-Capstone-Project-CH2-PS025/mobile-development/blob/bashirhanafi-patch-2/Bangkit%20Capstone%20Project%20-%20Page%201%20(5).png

# Getting Started Application
  - ### Prerequisites
      - ##### Tools Sofware
        - [Android Studio](https://developer.android.com/studio)

      - #### Installation
        - Clone this repository
        - 
