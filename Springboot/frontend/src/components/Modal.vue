<template>
  <transition name="modal">
    <div class="modal-mask">
      <div class="modal-wrapper">
        <div class="modal-dialog" role="document">
          <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title text-center">{{ $t(title) }} </h5>
              <button
                type="button"
                class="close"
                data-dismiss="modal"
                aria-label="Close"
                @click="closeModal()"
              >
                <span aria-hidden="true">&times;</span>
              </button>
            </div>

            <form @submit.prevent="add">
              <div class="modal-body">
                <slot></slot>
              </div>
              <div class="modal-footer">
                <button
                  type="button"
                  class="btn btn-secondary"
                  @click="closeModal()"
                >
                  Close
                </button>
                <button type="submit" class="btn btn-primary">
                  Add
                </button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </transition>
</template>

<script>
export default {
  name: "Modal",
  props: {
    title: String,
    add: Function,
    handleModal: Function
  },
  mounted() {
    console.log(this.title);
  },
  methods: {
    closeModal() {
      this.handleModal(false);
    }
  },
  computed: {
    translateTitle() {
      console.log(this.$t(this.title));
      return this.$t(this.title);
    }
  }
};
</script>

<style>
.modal-mask {
  position: fixed;
  z-index: 9998;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: table;
  transition: opacity 0.3s ease;
}

.modal-wrapper {
  display: table-cell;
  vertical-align: middle;
}
</style>
