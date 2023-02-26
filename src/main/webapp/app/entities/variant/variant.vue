<template>
  <div>
    <h2 id="page-heading" data-cy="VariantHeading">
      <span v-text="$t('dentalApp.variant.home.title')" id="variant-heading">Variants</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('dentalApp.variant.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'VariantCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-variant"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('dentalApp.variant.home.createLabel')"> Create a new Variant </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && variants && variants.length === 0">
      <span v-text="$t('dentalApp.variant.home.notFound')">No variants found</span>
    </div>
    <div class="table-responsive" v-if="variants && variants.length > 0">
      <table class="table table-striped" aria-describedby="variants">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('dentalApp.variant.productId')">Product Id</span></th>
            <th scope="row"><span v-text="$t('dentalApp.variant.shopifyId')">Shopify Id</span></th>
            <th scope="row"><span v-text="$t('dentalApp.variant.title')">Title</span></th>
            <th scope="row"><span v-text="$t('dentalApp.variant.price')">Price</span></th>
            <th scope="row"><span v-text="$t('dentalApp.variant.discount')">Discount</span></th>
            <th scope="row"><span v-text="$t('dentalApp.variant.option1')">Option 1</span></th>
            <th scope="row"><span v-text="$t('dentalApp.variant.option2')">Option 2</span></th>
            <th scope="row"><span v-text="$t('dentalApp.variant.option3')">Option 3</span></th>
            <th scope="row"><span v-text="$t('dentalApp.variant.imageId')">Image Id</span></th>
            <th scope="row"><span v-text="$t('dentalApp.variant.weight')">Weight</span></th>
            <th scope="row"><span v-text="$t('dentalApp.variant.compareAtPrice')">Compare At Price</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="variant in variants" :key="variant.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'VariantView', params: { variantId: variant.id } }">{{ variant.id }}</router-link>
            </td>
            <td>{{ variant.productId }}</td>
            <td>{{ variant.shopifyId }}</td>
            <td>{{ variant.title }}</td>
            <td>{{ variant.price }}</td>
            <td>{{ variant.discount }}</td>
            <td>{{ variant.option1 }}</td>
            <td>{{ variant.option2 }}</td>
            <td>{{ variant.option3 }}</td>
            <td>{{ variant.imageId }}</td>
            <td>{{ variant.weight }}</td>
            <td>{{ variant.compareAtPrice }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'VariantView', params: { variantId: variant.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'VariantEdit', params: { variantId: variant.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(variant)"
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
        ><span id="dentalApp.variant.delete.question" data-cy="variantDeleteDialogHeading" v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-variant-heading" v-text="$t('dentalApp.variant.delete.question', { id: removeId })">
          Are you sure you want to delete this Variant?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-variant"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeVariant()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./variant.component.ts"></script>
