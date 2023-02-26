/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';

import OrderDetailsService from '@/entities/order-details/order-details.service';
import { OrderDetails } from '@/shared/model/order-details.model';

const error = {
  response: {
    status: null,
    data: {
      type: null,
    },
  },
};

const axiosStub = {
  get: sinon.stub(axios, 'get'),
  post: sinon.stub(axios, 'post'),
  put: sinon.stub(axios, 'put'),
  patch: sinon.stub(axios, 'patch'),
  delete: sinon.stub(axios, 'delete'),
};

describe('Service Tests', () => {
  describe('OrderDetails Service', () => {
    let service: OrderDetailsService;
    let elemDefault;

    beforeEach(() => {
      service = new OrderDetailsService();
      elemDefault = new OrderDetails(123, 0, 0, 0, 'AAAAAAA', 'AAAAAAA', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign({}, elemDefault);
        axiosStub.get.resolves({ data: returnedFromService });

        return service.find(123).then(res => {
          expect(res).toMatchObject(elemDefault);
        });
      });

      it('should not find an element', async () => {
        axiosStub.get.rejects(error);
        return service
          .find(123)
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should create a OrderDetails', async () => {
        const returnedFromService = Object.assign(
          {
            id: 123,
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);

        axiosStub.post.resolves({ data: returnedFromService });
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not create a OrderDetails', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a OrderDetails', async () => {
        const returnedFromService = Object.assign(
          {
            orderId: 1,
            productId: 1,
            productType: 1,
            avatar: 'BBBBBB',
            name: 'BBBBBB',
            price: 1,
            point: 1,
            qty: 1,
            discount: 1,
            subtotalAmount: 1,
            totalAmount: 1,
            variantId: 1,
            shopifyVariantId: 1,
            shopifyProductId: 1,
            shopifyOrderId: 1,
            shopifyId: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a OrderDetails', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a OrderDetails', async () => {
        const patchObject = Object.assign(
          {
            orderId: 1,
            productType: 1,
            avatar: 'BBBBBB',
            name: 'BBBBBB',
            qty: 1,
            shopifyOrderId: 1,
          },
          new OrderDetails()
        );
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a OrderDetails', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of OrderDetails', async () => {
        const returnedFromService = Object.assign(
          {
            orderId: 1,
            productId: 1,
            productType: 1,
            avatar: 'BBBBBB',
            name: 'BBBBBB',
            price: 1,
            point: 1,
            qty: 1,
            discount: 1,
            subtotalAmount: 1,
            totalAmount: 1,
            variantId: 1,
            shopifyVariantId: 1,
            shopifyProductId: 1,
            shopifyOrderId: 1,
            shopifyId: 1,
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve().then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of OrderDetails', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a OrderDetails', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a OrderDetails', async () => {
        axiosStub.delete.rejects(error);

        return service
          .delete(123)
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });
    });
  });
});
