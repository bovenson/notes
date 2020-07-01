# toastor

## Demo

[Demo](http://codeseven.github.io/toastr/demo.html)

## 示例配置

```javascript
toastr.options = {
  "closeButton": false,
  "debug": false,
  "newestOnTop": false,
  "progressBar": false,
  "positionClass": "toast-top-right",
  "preventDuplicates": false,
  "onclick": null,
  "showDuration": "300",
  "hideDuration": "500",
  "timeOut": "1200",
  "extendedTimeOut": "500",
  "showEasing": "swing",
  "hideEasing": "linear",
  "showMethod": "fadeIn",
  "hideMethod": "fadeOut"
}
```

## 使用

```javascript
toastr["success"]("Have fun storming the castle!")
toastr["warning"]("My name is Inigo Montoya. You killed my father. Prepare to die!")
toastr["info"]("Are you the six fingered man?")
toastr["error"]("Inconceivable!")
```

