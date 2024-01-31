Overview:

    common:
    
        StoreOp - contains the remote interface that the Store class is bases off off
    
    controller:

        StoreController - gets user input and verifies that it is valid

    model:

        Account - parent class of the Users and Administrators classes which holds universal account information
        Administrators - in charge of the stors operations and are identified by password "779988"
        Users - the store's customers that can add items to their cart, buy their cart, and add money
        Items - the products that the store sells
        Store - the main class that incorperates all the other model classes to make a functioning store
        RemoteStoreServer - used to create the server which the clients (AKA customers & admins) will use to access the store object

    view:

        StoreView - contains the UI for the store and is also how the clients connect to the server

-------------------------------------------------------------------------------------------------------------------------------------

How To Run:

    1) make clean

    2) make

    3) kill any running rmiregistry (kill -9 <pid>)

    4) make setup

    5) make runServer (in-csci-rrpc01.cs.iupui.edu)

    6) make runClient (in-csci-rrpc02.cs.iupui.edu) (or any other rrpc other than 01)
