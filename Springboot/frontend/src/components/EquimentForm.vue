<template>
  <div>
    <div class="form-group">
      <label for="name">{{ $t("Name") }}:</label>
      <input
        v-model="equiment.name"
        type="text"
        class="form-control"
        id="name"
        name="name"
        required
      />
    </div>
    <div class="form-group">
      <label for="quantity"> {{ $t("Quantity") }}:</label>
      <input
        v-model="equiment.quantity"
        type="number"
        class="form-control"
        id="quantity"
        name="quantity"
        min="0"
        required
      />
    </div>

    <div class="form-group">
        <label for="cost">{{ $t("Cost") }}:</label>
      <input
        v-model="equiment.cost"
        type="number"
        class="form-control"
        id="cost"
        name="cost"
        min="0"
        step="0.01"
        required
      />
    </div>

    <div class="form-group">
      <label for="image">{{ $t("InsertImage") }}</label>
      <input type="file" @change="onFileChange" class="form-control-file" id="image" name="image" />
    </div>
  </div>
</template>

<script>
export default {
  name: "EquimentForm",
  props: {
    update: Boolean,
    equiment: Object
  },
    methods: {
        onFileChange(e) {
          var files = e.target.files || e.dataTransfer.files;
          if (!files.length) return;
          this.equiment.image = files[0];
        }
    }
};
</script>
