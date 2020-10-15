<template>
  <div>
    <div class="form-group">
      <label for="cedula">{{ $t("Cedula") }}: </label>
      <input
        type="text"
        v-model="client.cedula"
        class="form-control"
        id="cedula"
        name="cedula"
        required
      />
    </div>
    <div class="form-group">
      <label for="firstName">{{ $t("firstName") }} :</label>
      <input
        type="text"
        v-model="client.firstName"
        class="form-control"
        id="firstName"
        name="firstName"
        required
      />
    </div>

    <div class="form-group">
      <label for="lastName">{{ $t("lastName") }}:</label>
      <input
        type="text"
        v-model="client.lastName"
        class="form-control"
        id="lastName"
        name="lastName"
        required
      />
    </div>

    <div class="form-group">
      <label for="email">{{ $t("Email") }} :</label>
      <input
        type="email"
        class="form-control"
        v-model="client.email"
        id="email"
        name="email"
        required
      />
    </div>

    <div class="form-group">
      <label for="image"> {{ $t("InsertImage") }} :</label>
      <input
        type="file"
        class="form-control-file"
        id="image"
        name="image"
        @change="onFileChange"
      />
    </div>
  </div>
</template>

<script>
export default {
  name: "ClientForm",
  props: {
    client: Object,
    update: Boolean
  },
  methods: {
    onFileChange(e) {
      var files = e.target.files || e.dataTransfer.files;
      if (!files.length) return;
      this.client.image = files[0];
    }
  },
  mounted() {
    console.log(this.client);
  }
};
</script>
