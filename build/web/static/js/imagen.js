let archivos = [];
let enviarArchivos = [];
class Imagen {

    static select(files) {
        Imagen.convertirImg(files);

    }

    static enterImg(e) {
        e.preventDefault();
    }

    static overImg(e) {
        e.preventDefault();
    }

    static dropImg(e) {
        e.preventDefault();
        Imagen.convertirImg(e.dataTransfer.files);
    }

    static convertirImg(files) {
        if (files && files.length < 6 && archivos.length < 6) {


            let temp = '';
            for (let i = 0; i < files.length; i++) {

                let fileReader = new FileReader();

                if (files[i].type.match(/^image\//) && files[i].size > 512000) {
                    temp +=
                            `<p id="fu-${i}">${files[i].name} el archivo tiene mas de 500Kb</p>`;
                    document.querySelector('#panelU').innerHTML = temp;

                    continue;

                }

                fileReader.readAsDataURL(files[i]);


                temp +=
                        `<p id="fu-${i}">${files[i].name} loading</p>`;

                document.querySelector('#panelU').innerHTML = temp;

                document.querySelector(".spin").classList.add("spinner");

                fileReader.addEventListener("load", e => {

                    archivos.push(e.target.result);
                    enviarArchivos.push(e.target.result);

                    document.querySelector(`#fu-${i}`).innerText = `${files[i].name} loaded`;
                    document.querySelector(".spin").classList.remove("spinner");
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

    static rango() {
        const r = document.querySelector('#maxHeight');
        document.querySelector('#valorRango').innerText = r.value;
        return r.value;
    }

    static redimensionar(e) {
        e.preventDefault();
        if (archivos) {
            const size = Imagen.rango();
            Imagen.resize(size);

        }

    }

    static resize(size) {

        for (let i = 0; i < archivos.length; i++) {

            let image = new Image();
            image.src = archivos[i];
            
            document.querySelector(".spin").classList.add("spinner");
            
            image.onload = () => {

                let width = image.width;
                let height = image.height;

                let canvas = document.createElement('canvas');

                let escala = size / height;
                canvas.height = size;
                canvas.width = width * escala;

                canvas.getContext('2d').drawImage(image, 0, 0, width * escala, size);
                enviarArchivos[i] = canvas.toDataURL();

                document.querySelector(`#fu-${i}`).innerText = 'resized completed!!';
                document.querySelector(".spin").classList.remove("spinner");
            };

        }
    }

    static async traer() {
        /*
         const url = 'UploadImgServer?&q=';
         const param = {id: '' + 1};
         const response = await Http.get(url + JSON.stringify(param));
         const img = await JSON.parse(response.img);
         */
        
         const url = 'UploadImgServer';
         const response = await Http.get(url);
         
         let template =
         `${response.map(i =>
         `<img src="${i.img}" style = width:100px alt="img"/>`
         ).join(' ')}`;
         
         document.querySelector('#panel').innerHTML = template;
     

    }

    static async subir() {
        if (enviarArchivos && enviarArchivos.length > 0) {
            const url = 'UploadImgServer';
            //const img = JSON.stringify(archivos);
            const response = await Http.post(url, enviarArchivos);
            archivos = [];
            enviarArchivos = [];
            console.log(response);

        } else
            console.log("no hay imagenes por subir")
    }

}

