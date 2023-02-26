<template>
  <div>
    <h2 id="page-heading" data-cy="ProductUserHeading">
      <span v-text="$t('dentalApp.productUser.home.title')" id="product-user-heading">Product Users</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('dentalApp.productUser.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'ProductUserCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-product-user"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('dentalApp.productUser.home.createLabel')"> Create a new Product User </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && productUsers && productUsers.length === 0">
      <span v-text="$t('dentalApp.productUser.home.notFound')">No productUsers found</span>
    </div>
    <div class="table-responsive" v-if="productUsers && productUsers.length > 0">
      <table class="table table-striped" aria-describedby="productUsers">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('dentalApp.productUser.userId')">User Id</span></th>
            <th scope="row"><span v-text="$t('dentalApp.productUser.productId')">Product Id</span></th>
            <th scope="row"><span v-text="$t('dentalApp.productUser.productType')">Product Type</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="productUser in productUsers" :key="productUser.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'ProductUserView', params: { productUserId: productUser.id } }">{{ productUser.id }}</router-link>
            </td>
            <td>{{ productUser.userId }}</td>
            <td>{{ productUser.productId }}</td>
            <td>{{ productUser.productType }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'ProductUserView', params: { productUserId: productUser.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'ProductUserEdit', params: { productUserId: productUser.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(productUser)"
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
        ><span id="dentalApp.productUser.delete.question" data-cy="productUserDeleteDialogHeading" v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-productUser-heading" v-text="$t('dentalApp.productUser.delete.question', { id: removeId })">
          Are you sure you want to delete this Product User?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-productUser"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeProductUser()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./product-user.component.ts"></script>
