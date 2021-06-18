const host = process.env.VUE_APP_URL;
const port = process.env.VUE_APP_PORT;
module.exports = {
  outputDir: "../backend/src/main/resources/static",  
  indexPath: "../static/index.html",  
  devServer: {
    overlay: false,
    port: port,
    proxy: {
      "/api": {
        target: `http://${host}:${process.env.VUE_APP_PROXY_PORT}`,
        changeOrigin: true
      },
      "/user":{
        target: `http://${host}:${process.env.VUE_APP_PROXY_PORT}`,
        changeOrigin: true
      }
    },
  },
  chainWebpack: config => {  
    const svgRule = config.module.rule("svg");    
    svgRule.uses.clear();    
    svgRule.use("vue-svg-loader").loader("vue-svg-loader");  
  }  
};
