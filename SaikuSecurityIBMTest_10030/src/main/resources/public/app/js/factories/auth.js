    app.factory('authFactory', ['$cookieStore', function ($cookieStore) {

      return {
        global: {
              loggedIn: false
            }
      };
    }])
;
