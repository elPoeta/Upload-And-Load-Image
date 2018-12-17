class Http{

	static async get(url) {
        try{
        const response = await fetch(url, { 
            method: 'GET',
             headers: {
                'Content-type': 'application/json; charset=UTF-8',
            },
           // credentials: 'include'
            credentials: 'same-origin'
           });
           
        let data = JSON.parse(await response.text());
        return data;
          }catch (error) {
         throw new Error(error);
  }
    }
	
	static async post(url, data) {
        try{    
        const response = await fetch(url, {
            method: 'POST',
            headers: {
                'Content-type': 'application/json; charset=UTF-8'
            },
            body: JSON.stringify(data),
             //credentials: 'include'  */
              credentials: 'same-origin' 
        });
            return response;
        }catch (error) {
         throw new Error(error);
        }
  }      
   	static async put(url, data) {
        try{    
        const response = await fetch(url, {
            method: 'PUT',
            headers: {
                'Content-type': 'application/json; charset=UTF-8'
       
                
            },
            body: JSON.stringify(data),
            // credentials: 'include' 
              credentials: 'same-origin' 
        });
            return response;
        }catch (error) {
         throw new Error(error);
    }
  } 
    
     static async delete(url) {
        try{        
          const response = await fetch(url, {
            method: 'DELETE',
             headers: {
                'Content-type': 'application/json; charset=UTF-8'
              
            },
            credentials: 'same-origin'
           });
          return response;
        }catch (error) {
          throw new Error(error);
        }
    }
}
