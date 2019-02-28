import { Inject, Injectable } from '@angular/core';
import { LOCAL_STORAGE, StorageService } from 'ngx-webstorage-service';
import {Player} from '../Player';

@Injectable()
export class LocalStorageService {

  signedInUser: Player;

  constructor(@Inject(LOCAL_STORAGE) private storage: StorageService) {
  }

  public storeUser(player: Player): void {
    this.storage.set('signedInPlayer', player);
  }

  public getStoredUser(): Player {
    return this.storage.get('signedInPlayer');
  }

  public logOutUser() {
     localStorage.removeItem('signedInPlayer');
  }

}
