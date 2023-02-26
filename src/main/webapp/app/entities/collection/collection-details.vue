<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="collection">
        <h2 class="jh-entity-heading" data-cy="collectionDetailsHeading">
          <span v-text="$t('dentalApp.collection.detail.title')">Collection</span> {{ collection.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('dentalApp.collection.shopifyId')">Shopify Id</span>
          </dt>
          <dd>
            <span>{{ collection.shopifyId }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.collection.title')">Title</span>
          </dt>
          <dd>
            <span>{{ collection.title }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.collection.description')">Description</span>
          </dt>
          <dd>
            <span>{{ collection.description }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.collection.handle')">Handle</span>
          </dt>
          <dd>
            <span>{{ collection.handle }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link
          v-if="collection.id"
          :to="{ name: 'CollectionEdit', params: { collectionId: collection.id } }"
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

<script lang="ts" src="./collection-details.component.ts"></script>
