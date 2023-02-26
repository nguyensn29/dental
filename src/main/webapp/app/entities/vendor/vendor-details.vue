<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="vendor">
        <h2 class="jh-entity-heading" data-cy="vendorDetailsHeading">
          <span v-text="$t('dentalApp.vendor.detail.title')">Vendor</span> {{ vendor.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('dentalApp.vendor.name')">Name</span>
          </dt>
          <dd>
            <span>{{ vendor.name }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link v-if="vendor.id" :to="{ name: 'VendorEdit', params: { vendorId: vendor.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./vendor-details.component.ts"></script>
