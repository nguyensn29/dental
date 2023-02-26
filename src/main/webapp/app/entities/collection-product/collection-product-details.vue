<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="collectionProduct">
        <h2 class="jh-entity-heading" data-cy="collectionProductDetailsHeading">
          <span v-text="$t('dentalApp.collectionProduct.detail.title')">CollectionProduct</span> {{ collectionProduct.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('dentalApp.collectionProduct.collectionId')">Collection Id</span>
          </dt>
          <dd>
            <span>{{ collectionProduct.collectionId }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.collectionProduct.productId')">Product Id</span>
          </dt>
          <dd>
            <span>{{ collectionProduct.productId }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link
          v-if="collectionProduct.id"
          :to="{ name: 'CollectionProductEdit', params: { collectionProductId: collectionProduct.id } }"
          custom
          v-slot="{ navigate }"
        >
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./collection-product-details.component.ts"></script>
