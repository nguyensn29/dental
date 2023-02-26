<template>
  <div>
    <h2 id="page-heading" data-cy="OrderDetailsHeading">
      <span v-text="$t('dentalApp.orderDetails.home.title')" id="order-details-heading">Order Details</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('dentalApp.orderDetails.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'OrderDetailsCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-order-details"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('dentalApp.orderDetails.home.createLabel')"> Create a new Order Details </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && orderDetails && orderDetails.length === 0">
      <span v-text="$t('dentalApp.orderDetails.home.notFound')">No orderDetails found</span>
    </div>
    <div class="table-responsive" v-if="orderDetails && orderDetails.length > 0">
      <table class="table table-striped" aria-describedby="orderDetails">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('dentalApp.orderDetails.orderId')">Order Id</span></th>
            <th scope="row"><span v-text="$t('dentalApp.orderDetails.productId')">Product Id</span></th>
            <th scope="row"><span v-text="$t('dentalApp.orderDetails.productType')">Product Type</span></th>
            <th scope="row"><span v-text="$t('dentalApp.orderDetails.avatar')">Avatar</span></th>
            <th scope="row"><span v-text="$t('dentalApp.orderDetails.name')">Name</span></th>
            <th scope="row"><span v-text="$t('dentalApp.orderDetails.price')">Price</span></th>
            <th scope="row"><span v-text="$t('dentalApp.orderDetails.point')">Point</span></th>
            <th scope="row"><span v-text="$t('dentalApp.orderDetails.qty')">Qty</span></th>
            <th scope="row"><span v-text="$t('dentalApp.orderDetails.discount')">Discount</span></th>
            <th scope="row"><span v-text="$t('dentalApp.orderDetails.subtotalAmount')">Subtotal Amount</span></th>
            <th scope="row"><span v-text="$t('dentalApp.orderDetails.totalAmount')">Total Amount</span></th>
            <th scope="row"><span v-text="$t('dentalApp.orderDetails.variantId')">Variant Id</span></th>
            <th scope="row"><span v-text="$t('dentalApp.orderDetails.shopifyVariantId')">Shopify Variant Id</span></th>
            <th scope="row"><span v-text="$t('dentalApp.orderDetails.shopifyProductId')">Shopify Product Id</span></th>
            <th scope="row"><span v-text="$t('dentalApp.orderDetails.shopifyOrderId')">Shopify Order Id</span></th>
            <th scope="row"><span v-text="$t('dentalApp.orderDetails.shopifyId')">Shopify Id</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="orderDetails in orderDetails" :key="orderDetails.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'OrderDetailsView', params: { orderDetailsId: orderDetails.id } }">{{
                orderDetails.id
              }}</router-link>
            </td>
            <td>{{ orderDetails.orderId }}</td>
            <td>{{ orderDetails.productId }}</td>
            <td>{{ orderDetails.productType }}</td>
            <td>{{ orderDetails.avatar }}</td>
            <td>{{ orderDetails.name }}</td>
            <td>{{ orderDetails.price }}</td>
            <td>{{ orderDetails.point }}</td>
            <td>{{ orderDetails.qty }}</td>
            <td>{{ orderDetails.discount }}</td>
            <td>{{ orderDetails.subtotalAmount }}</td>
            <td>{{ orderDetails.totalAmount }}</td>
            <td>{{ orderDetails.variantId }}</td>
            <td>{{ orderDetails.shopifyVariantId }}</td>
            <td>{{ orderDetails.shopifyProductId }}</td>
            <td>{{ orderDetails.shopifyOrderId }}</td>
            <td>{{ orderDetails.shopifyId }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'OrderDetailsView', params: { orderDetailsId: orderDetails.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'OrderDetailsEdit', params: { orderDetailsId: orderDetails.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(orderDetails)"
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
        ><span id="dentalApp.orderDetails.delete.question" data-cy="orderDetailsDeleteDialogHeading" v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-orderDetails-heading" v-text="$t('dentalApp.orderDetails.delete.question', { id: removeId })">
          Are you sure you want to delete this Order Details?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-orderDetails"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeOrderDetails()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./order-details.component.ts"></script>
