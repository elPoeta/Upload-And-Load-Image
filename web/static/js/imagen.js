let archivos = [];
class Imagen {

    static select(files) {
        Imagen.convertirImg(files);
       
    }
    
    static enterImg(e){
        e.preventDefault();
    }
    
    static overImg(e){
        e.preventDefault();
    }
    
    static dropImg(e){
        e.preventDefault();
        console.log(e.dataTransfer.files);
        Imagen.convertirImg(e.dataTransfer.files);
    }
    
    static convertirImg(files){
         if (files && files.length < 6) {

           
            let temp = '';
            for (let i = 0; i < files.length; i++) {

                let fileReader = new FileReader();

                if (files[i].type.match(/^image\//) && files[i].size > 1048576){
                    temp +=
                        `<p id="fu-${i}">${files[i].name} el archivo tiene mas de 1MB</p>`;
                    document.querySelector('#panelU').innerHTML = temp;
                 
                    continue;
                    
                }
                fileReader.readAsDataURL(files[i]);


                temp +=
                        `<p id="fu-${i}">${files[i].name} loading</p>`;

                document.querySelector('#panelU').innerHTML = temp;

                fileReader.addEventListener("load", e => {

                    archivos.push(e.target.result);
                    document.querySelector(`#fu-${i}`).innerText = `${files[i].name} loaded`;

                });
            }
        } else {
            let template = '';
            if (files.length > 5) {
                template = `<p><strong>Solo 5 files, selectd: ${files.length}</strong></p>`;
                console.log('no archivo');
            } else if (files) {
                template = `<p><strong>El archivo seleccionado no es una imagen, es de tipo: ${files[0].type}</strong></p>`;
                console.log('no archivo');
            }

            document.querySelector('#panelU').innerHTML = template;

        }

    }

    static async traer() {
        const url = 'UploadImgServer?&q=';
        const param = {id: '' + 1};
        const response = await Http.get(url + JSON.stringify(param));
        const img = await JSON.parse(response.img);
      

        let template =
                `${img.map(i =>
                        `<img src="${i}" style = width:100px alt="img"/>`
                ).join(' ')}`;

        document.querySelector('#panel').innerHTML = template;

    }

    static async subir() {
        if (archivos && archivos.length > 0) {
            const url = 'UploadImgServer';
            const img = JSON.stringify(archivos);
            const response = await Http.post(url, {img});
            archivos = [];
            console.log(response);

        } else
            console.log("no hay imagenes por subir")
    }

}

