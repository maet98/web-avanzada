<template>
  <div>
    <div class="form-group">
      <label for="name">{{ $t("Name") }}:</label>
      <input
        v-model="name"
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
        v-model="quantity"
        type="number"
        class="form-control"
        id="quantity"
        name="quantity"
        min="0"
        required
      />
    </div>

    <div class="form-group">
      <label for="cost">{{ $t("Cost"):</label>
      <input
        v-model="cost"
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
      <input type="file" class="form-control-file" id="image" name="image" />
    </div>
    <button type="submit" class="btn btn-primary">Register</button>
  </div>
</template>

<script>
export default {
  name: "EquimentForm",
  props: {
    update: Boolean,
    equiment: Object
  },
  data() {
    return {
      name: "",
      quantity: 0,
      cost: 0.0,
      image: null
    };
  }
};
</script>
