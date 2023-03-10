<template>
  <div>
    <h2 id="page-heading" data-cy="CartHeading">
      <span v-text="$t('dentalApp.cart.home.title')" id="cart-heading">Carts</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('dentalApp.cart.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'CartCreate' }" custom v-slot="{ navigate }">
          <button @click="navigate" id="jh-create-entity" data-cy="entityCreateButton" class="btn btn-primary jh-create-entity create-cart">
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('dentalApp.cart.home.createLabel')"> Create a new Cart </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && carts && carts.length === 0">
      <span v-text="$t('dentalApp.cart.home.notFound')">No carts found</span>
    </div>
    <div class="table-responsive" v-if="carts && carts.length > 0">
      <table class="table table-striped" aria-describedby="carts">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('dentalApp.cart.userId')">User Id</span></th>
            <th scope="row"><span v-text="$t('dentalApp.cart.productId')">Product Id</span></th>
            <th scope="row"><span v-text="$t('dentalApp.cart.variantId')">Variant Id</span></th>
            <th scope="row"><span v-text="$t('dentalApp.cart.productType')">Product Type</span></th>
            <th scope="row"><span v-text="$t('dentalApp.cart.qty')">Qty</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="cart in carts" :key="cart.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'CartView', params: { cartId: cart.id } }">{{ cart.id }}</router-link>
            </td>
            <td>{{ cart.userId }}</td>
            <td>{{ cart.productId }}</td>
            <td>{{ cart.variantId }}</td>
            <td>{{ cart.productType }}</td>
            <td>{{ cart.qty }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'CartView', params: { cartId: cart.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'CartEdit', params: { cartId: cart.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(cart)"
                  variant="danger"
                  class="btn btn-sm"
                  data-cy="entityDeleteButton"
                  v-b-modal.removeEntity
                >
                  <font-awesome-icon icon="times"></font-awesome-icon>
                  <span class="d-none d-md-inline" v-text="$t('entity.action.delete')">Delete</span>
                </b-button>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <b-modal ref="removeEntity" id="removeEntity">
      <span slot="modal-title"
        ><span id="dentalApp.cart.delete.question" data-cy="cartDeleteDialogHeading" v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-cart-heading" v-text="$t('dentalApp.cart.delete.question', { id: removeId })">
          Are you sure you want to delete this Cart?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-cart"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeCart()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./cart.component.ts"></script>
