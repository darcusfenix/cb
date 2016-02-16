<%--
  Created by IntelliJ IDEA.
  User: becm
  Date: 2/4/16
  Time: 11:20 AM
--%>

<div class="row static-info">
    <div class="col-md-5 name">
        Nombres:
    </div>

    <div class="col-md-7 value">
        {{vendedor.nombres}}
    </div>
</div>

<div class="row static-info">
    <div class="col-md-5 name">
        Apellidos:
    </div>

    <div class="col-md-7 value">
        {{vendedor.apellidos}}
    </div>
</div>

<div class="row static-info">
    <div class="col-md-5 name">
        correo electrónico:
    </div>

    <div class="col-md-7 value">
        {{vendedor.username}}
    </div>
</div>

<div class="row static-info">
    <div class="col-md-5 name">
        Estado:
    </div>

    <div class="col-md-7 value">
        <span class="label label-success "
              ng-class="vendedor.activo ? 'label-success' : 'label-danger'">
            {{ vendedor.activo ? 'Activo': 'bloqueado'}}</span>
    </div>
</div>