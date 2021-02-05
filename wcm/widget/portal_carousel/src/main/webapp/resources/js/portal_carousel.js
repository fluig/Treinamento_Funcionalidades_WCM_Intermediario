var portalCarousel = SuperWidget.extend({
    //variáveis da widget
    variavelNumerica: null,
    variavelCaracter: null,

    //método iniciado quando a widget é carregada
    init: function() {
    	this.iniciarCarrousel();
    },
  
    //BIND de eventos
    bindings: {
        local: {
            'execute': ['click_executeAction']
        },
        global: {}
    },
 
    iniciarCarrousel: function() {
    	var images = [
              {
                  src: '/portal_carousel/resources/images/img1.jpg',
                  alt: '${i18n.getTranslation("carrousel.img1.title")}',
                  title: '${i18n.getTranslation("carrousel.img1.title")}',
                  description: '${i18n.getTranslation("carrousel.img1.descr")}',
                  linktarget: '_blank',
                  linkhref: 'http://www.fluig.com',
                  linktext: 'fluig'
              },
              {
                  src: '/portal_carousel/resources/images/img2.jpg',
                  alt: '${i18n.getTranslation("carrousel.img2.title")}',
                  title: '${i18n.getTranslation("carrousel.img2.title")}',
                  description: '${i18n.getTranslation("carrousel.img2.descr")}',
                  linktarget: '_blank',
                  linkhref: 'http://style.fluig.com',
                  linktext: 'Style Guide'
              }
          ];
           
          var settings = {
              id: 'myFluigCarouselExample',
              images: images,
              indicators: true,
              startIndex: 0,
              interval: 5000,
              autoSize:true,
              resize:true
          };
           
          var carousel = FLUIGC.carousel('#carousel-example-generic_'+this.instanceId, settings);
    }

});

