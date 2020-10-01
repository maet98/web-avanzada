<!DOCTYPE html>
<html lang="en">
<head>
    <title>Equiments</title>
    <!-- CSS only -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">

    <!-- JS, Popper.js, and jQuery -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
</head>
<body>
<div class="card text-center">
    <div class="card-header">
        <h2>List of equiments:</h2>
    </div>
    <div class="card-body">
        <div class="text-right" style="margin: 12px">
            <a href="/equiment/register" >
                <button class="btn btn-primary text-right">New User</button>
            </a>
        </div>
        <table class="table ">
            <thead class="thead-dark">
            <tr>
                <th>#</th>
                <th>Image</th>
                <th>Name</th>
                <th>Quantity</th>
                <th>Cost</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <#list equiments as equiment>
                <tr>
                    <th>${equiment.id}</th>
                    <td><img src="${equiment.image}"/></td>
                    <td>${equiment.name}</td>
                    <td>${equiment.quantity}</td>
                    <td>${equiment.cost}</td>
                    <td>
                        <a href="${"/user/update/"+ user.cedula}">
                            <button class="btn btn-warning" >Update</button>
                        </a>
                        <a href="${"/user/delete/"+ user.cedula}">
                            <button class="btn btn-danger" >Delete</button>
                        </a>
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
