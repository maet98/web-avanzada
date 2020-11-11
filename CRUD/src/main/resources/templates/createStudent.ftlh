<!DOCTYPE html>
<html lang="en">
    <head>
        <!-- CSS only -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">

        <title><#if add>Create a Student<#else>Edit a Student</#if></title>
        <!-- JS, Popper.js, and jQuery -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
    </head>
    <body>
        <#if add>
            <#assign urlAction>/student/create</#assign>
            <#assign submitTitle>Create</#assign>
        <#else>
            <#assign urlAction>${'/student/update/' + student.matricula?long?c}</#assign>
            <#assign submitTitle>Update</#assign>
        </#if>
        <div class="card">
            <div class="card-header">
                <h2><#if add>Create a Student<#else>Edit a Student</#if></h2>
            </div>
            <div class="card-body">
                <div class="text-right m4">
                    <a href="/student">
                        <button class="btn btn-primary">Volver a home</button>
                    </a>
                </div>
                <form action="${urlAction}" name="student" method="POST">
                    <div class="form-group">
                        <label for="matricula">Id</label>
                        <#if add>
                                <input class="from-control" id="matricula" value="${(student.matricula)!''}" name="matricula" placeholder="20170200" required/>
                            <#else>
                                <input disabled class="from-control" id="matricula" value="${(student.matricula?long?c)!''}" name="matricula" placeholder="2017-0200" required/>
                        </#if>
                    </div>
                    <div class="form-group">
                        <label for="nombre">First Name</label>
                        <input class="from-control" id="nombre" name="nombre" value="${(student.nombre)!''}" placeholder="Miguel" required/>
                    </div>
                    <div class="form-group">
                        <label for="apellido">Last Name</label>
                        <input class="from-control" id="apellido" name="apellido" value="${(student.apellido)!''}" placeholder="Estevez" required/>
                    </div>
                    <div class="form-group">
                        <label for="telefono">Telephone</label>
                        <input class="from-control" id="telefono" name="telefono" value="${(student.telefono)!''}" placeholder="809-9090-9999" required/>
                    </div>
                    <button type="submit" class="btn btn-primary"><#if add>Add <#else> Update</#if></button>
                    <button type="reset" class="btn btn-secondary">Reset</button>
                </form>
            </div>
    </body>
</html>