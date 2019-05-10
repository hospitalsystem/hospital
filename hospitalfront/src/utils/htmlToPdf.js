// 导出页面为PDF格式
import html2Canvas from 'html2canvas'
import JsPDF from 'jspdf'
export default{
  install (Vue, options) {
    Vue.prototype.getPdf = function () {

      //pdf默认的名字
      var title = this.htmlTitle;
      let pdfDom = document.querySelector('#pdfDom')

      //隐藏input边框
      let arrnode = pdfDom.querySelectorAll('input')
      for (var i = 0; i < arrnode.length; i++) {
        arrnode[i].style.border = 'hidden'
      }

      html2Canvas(pdfDom,
        {
        allowTaint: true
      }).then(function (canvas) {
          let contentWidth = canvas.width
          let contentHeight = canvas.height
          let pageHeight = contentWidth / 592.28 * 841.89

          //未生成pdf的html页面的高度
          let leftHeight = contentHeight
          //页面偏移
          let position = 0
        //a4纸的尺寸[595.28,841.89]
          let imgWidth = 595.28
          let imgHeight = 592.28 / contentWidth * contentHeight
          let pageData = canvas.toDataURL('image/jpeg', 1.0)
          let PDF = new JsPDF('', 'pt', 'a4')
        //当内容未超过pdf一页显示的范围，无需分页
          if (leftHeight < pageHeight) {
            PDF.addImage(pageData, 'JPEG', 3.5, 0, imgWidth, imgHeight)
          } else {
            //分页
            while (leftHeight > 0) {
              PDF.addImage(pageData, 'JPEG', 3.5, position, imgWidth, imgHeight)
              leftHeight -= pageHeight
              position -= 841.89
              //避免添加空白页
              if (leftHeight > 0) {
                PDF.addPage()
              }
            }
          }
          PDF.save(title + '.pdf')
        }
      )
    }
  }
}
